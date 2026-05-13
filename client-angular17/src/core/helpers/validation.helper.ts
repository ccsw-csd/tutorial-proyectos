export function validateFields <TData>(obj: Partial<TData>, validateFields: readonly string[]): obj is Required<TData> {
  return validateFields.every((field) => field in obj);
}